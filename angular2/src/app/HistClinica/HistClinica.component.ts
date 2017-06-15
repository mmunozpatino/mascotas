import {Component,OnInit} from '@angular/core';
import {HistClinicaService, HistoriaClinica} from './HistClinica.service';
import {HISTORIAS} from './HistClinica.bd';
import { ActivatedRoute, Router }   from '@angular/router';


@Component({
    selector: 'HistClinica',
    templateUrl: 'HistClinica.component.html'
})
export class HistClinica implements OnInit{
    fecha: string;
    historias: HistoriaClinica[];
    idMascota: string;
    nueva: boolean;
    constructor(
        private service: HistClinicaService,
        private route: ActivatedRoute,
        private router: Router
    ){}
    ngOnInit(){
        //this.historias = this.service.get();
        this.route.params.subscribe(params => {
            this.idMascota = params['id'];
            this.service.getByMascota(this.idMascota).then(hist => {
                this.historias = hist;
                console.log(this.historias);})
        })
    }
    goNew(){
        this.nueva = true;
    }
}