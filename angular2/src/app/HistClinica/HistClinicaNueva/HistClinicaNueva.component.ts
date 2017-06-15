import { Component, OnInit, Input } from '@angular/core';
import {HistoriaClinica, HistClinicaService} from '../HistClinica.service';
import {ActivatedRoute,Router} from '@angular/router';

@Component({
    selector: 'HistClincaNueva',
    templateUrl: 'HistClinicaNueva.component.html'
})
export class HistClinicaNueva implements OnInit{
    historia: HistoriaClinica;
    @Input() mascotaId: string;
    constructor(
        private service: HistClinicaService,
        private route: ActivatedRoute,
        private router: Router
    ){}
    ngOnInit(){
        this.historia = {id: null, mascota_id: this.mascotaId, fecha: '', titulo: '', descripcion: '', recordatorio: ''};
    }
    save(){
        this.historia.mascota_id = this.mascotaId;
        this.service.guardarHistoria(this.historia);
        this.router.navigate(['historia/'+this.mascotaId]);
        /*this.service.guardarHistoria(this.historia);
        console.log('Se guardo!');
        */

    }
}