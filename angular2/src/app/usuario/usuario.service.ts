import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UsuarioService extends RestBaseService {
  private loginUrl = '/rest/seguridad/login';
  private logoutUrl = '/rest/seguridad/logout';
  private principalUrl = '/rest/seguridad/principal';
  private registrarUrl = '/rest/usuarios';
  

  private usuarioLogueado;

  constructor(private http: Http) { super(); }

  login(username: string, password: string): Promise<Usuario> {
    const data = {
      'login': username,
      'password': password
    };

    return this.http.post(UsuarioService.serverUrl + this.loginUrl, JSON.stringify(data), this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Usuario;
      })
      .catch(this.handleError);
  }

  logout(): Promise<Usuario> {
    return this.http.get(UsuarioService.serverUrl + this.logoutUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Usuario;
      })
      .catch(this.handleError);
  }

  getPrincipal(): Promise<Usuario> {
    return this.http.get(UsuarioService.serverUrl + this.principalUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Usuario;
      })
      .catch(this.handleError);
  }

  registrarUsuario(value: RegistrarUsuario): Promise<Usuario> {
      return this.http.post(UsuarioService.serverUrl + this.registrarUrl, JSON.stringify(value), this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Usuario;
      })
      .catch(this.handleError);
  }
}

export interface RegistrarUsuario {
  login: string;
  nombre: string;
  email: string;
  password: string;
}

export interface Usuario {
  login: string;
  nombre: string;
  email: string;
  finVigencia: string;
  inicioVigencia: string;
  activo: boolean;
  roles: string[];
}

