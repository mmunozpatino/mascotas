import {Injectable} from '@angular/core';
import {HISTORIAS} from './HistClinica.bd';
import { Http, Headers, Response } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';
import { Mascota, MascotaService } from "app/mascota/mascota.service";


@Injectable()
export class HistClinicaService extends RestBaseService{
    
    mascota: Mascota;    
    
    private url = '/rest/historia';

  constructor(private http: Http, private serviceMascota: MascotaService) { super(); }
  get(): Promise<HistoriaClinica[]> {
    return this.http.get(HistClinicaService.serverUrl + this.url, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as HistoriaClinica[];
      })
      .catch(this.handleError);
  }
 
  getById(id: string): Promise<HistoriaClinica>{
    return this.http.get(HistClinicaService.serverUrl + this.url + '/detalle/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as HistoriaClinica;
      })
      .catch(this.handleError);
  }
  getByMascota(id: string): Promise<HistoriaClinica[]> {
    return this.http.get(HistClinicaService.serverUrl + this.url + '/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as HistoriaClinica[];
      })
      .catch(this.handleError);
  }
 
  guardarHistoria(value: HistoriaClinica, idm: number){
      return this.http.post(HistClinicaService.serverUrl + this.url + '/' + idm, JSON.stringify(value), this.getRestHeader())
      .toPromise()
      .then()
      .catch();
  }
  borrarHistoria(id: number){
    return this.http.delete(HistClinicaService.serverUrl + this.url + '/' + id, this.getRestHeader()).toPromise().then(() => console.log('se borro!'));
  }
  deleteByMascota(id: number){
    let historias: HistoriaClinica[];
    return this.getByMascota(id.toString()).then(hist => {historias = hist
          for(let h of historias){
            this.borrarHistoria(+h.id);
          }
  });

  }
}

export interface HistoriaClinica{
    id: string;
    fecha:string;
    titulo: string;
    descripcion: string;
    recordatorio: string;
}