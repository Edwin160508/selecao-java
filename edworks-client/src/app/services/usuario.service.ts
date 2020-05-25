import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../usuario/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private httpClient: HttpClient) { }

  salvar(usuario: Usuario): Observable<Usuario>{
    return this.httpClient.post<Usuario>('http://localhost:8080/usuario',usuario);
  }
}
