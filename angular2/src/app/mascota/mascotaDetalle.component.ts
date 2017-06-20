import { Component, OnInit } from '@angular/core';
import { MascotaService, Mascota } from './mascota.service'
import { Router, ActivatedRoute } from "@angular/router";
import { HistClinicaService, HistoriaClinica } from "app/HistClinica/HistClinica.service";


@Component({
    selector: 'mascotaDetalle',
    templateUrl: 'mascotaDetalle.component.html',
    styleUrls: ['mascotaDetalle.component.css']
})
export class MascotaDetalle implements OnInit{
    id: number;
    mascota: Mascota;
    historias: HistoriaClinica[];
    histBorradas: boolean;
    error: boolean;
    constructor(
        private service: MascotaService,
        private route: ActivatedRoute,
        private router: Router,
        private serviceHistoria: HistClinicaService
    ){}
    ngOnInit(){
        this.route.params.subscribe(params => {
            this.id = +params['id'];
            this.service.buscarMascota(this.id).then(m => this.mascota = m);
        });
        
    }
    delete(){
        
        if(this.histBorradas){            
            this.service.eliminarMascota(this.mascota.id);
            this.router.navigate(['mascotas']);
        }else{
            this.error=true;
        }
    }
    deleteHist(){
        this.serviceHistoria.deleteByMascota(this.mascota.id);
        this.histBorradas=true;
    }
    ok(){
        this.router.navigate(['mascotas']);
    }
    ocultarMsj(){
        this.error=false;
    }
}