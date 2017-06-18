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
        this.service.eliminarMascota(this.mascota.id);
        this.router.navigate(['mascotas']);
    }
    deleteHist(){
        this.serviceHistoria.deleteByMascota(this.mascota.id);
    }
    ok(){
        this.router.navigate(['mascotas']);
    }
}