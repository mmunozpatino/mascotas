import { Component, OnInit } from '@angular/core';
import { MascotaService, Mascota } from './mascota.service'
import { Observable } from 'rxjs/Rx';
import { Router } from "@angular/router";


@Component( {
    selector: 'app-mascota',
    templateUrl: './mascota.component.html',
    styleUrls: ['mascota.component.css']
})
export class MascotaComponent implements OnInit {
    errorMessage: string;
    mascotas: Mascota[];
    m: Mascota;

    constructor( private mascotasService: MascotaService,
                private router: Router ) { }

    ngOnInit() {
        this.mascotasService.buscarMascotas()
          .then(mascotas => this.mascotas = mascotas)
          .catch(error => this.errorMessage = <any>error );
    }
    detalle(m: Mascota){
        this.router.navigate(['mascotaDetalle/'+m.id]);
    }
}
