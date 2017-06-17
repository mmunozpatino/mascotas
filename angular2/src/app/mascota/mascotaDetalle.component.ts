import { Component, OnInit } from '@angular/core';
import { MascotaService, Mascota } from './mascota.service'
import { Router, ActivatedRoute } from "@angular/router";


@Component({
    selector: 'mascotaDetalle',
    templateUrl: 'mascotaDetalle.component.html',
    styleUrls: ['mascotaDetalle.component.css']
})
export class MascotaDetalle implements OnInit{
    id: number;
    mascota: Mascota;
    constructor(
        private service: MascotaService,
        private route: ActivatedRoute,
        private router: Router
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
    ok(){
        this.router.navigate(['mascotas']);
    }
}