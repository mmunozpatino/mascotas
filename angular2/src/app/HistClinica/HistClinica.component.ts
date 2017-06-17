import {Component,OnInit} from '@angular/core';
import {HistClinicaService, HistoriaClinica} from './HistClinica.service';
import {HISTORIAS} from './HistClinica.bd';
import { ActivatedRoute, Router }   from '@angular/router';


@Component({
    selector: 'HistClinica',
    templateUrl: 'HistClinica.component.html',
    styleUrls:['HistClinica.component.css']
})
export class HistClinica implements OnInit{
    fecha: string;
    historias: HistoriaClinica[];
    idMascota: string;
    nueva: boolean;
    historia: HistoriaClinica;
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
        this.router.navigate(['historiaNueva/'+this.idMascota]);
    }
    delete(id: number){
        this.service.borrarHistoria(id).then(()=> this.router.navigate(['historia/'+this.idMascota]));
        this.service.getById(id.toString()).then(hist => this.historia = hist);
        var n = this.historias.indexOf(this.historia);
        this.historias.splice(n,1);
        ;
    }
}