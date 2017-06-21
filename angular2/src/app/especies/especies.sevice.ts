import {Injectable} from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class EspeciesService extends RestBaseService{
    especie: Especie;

    private url ='/rest/especies';

    constructor(private http: Http) { super(); }

    get(): Promise<Especie[]> {
    return this.http.get(EspeciesService.serverUrl + this.url, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Especie[];
      })
      .catch(this.handleError);
  }
    getById(id: number){
      return this.http.get(EspeciesService.serverUrl + this.url + '/' + id , this.getRestHeader()).toPromise().then(response => {return response.json() as Especie});
    }
}

export interface Especie{
    id: number;
    nombreEspecie: string;
}