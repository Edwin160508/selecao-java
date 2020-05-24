import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  usuario: Usuario;
  constructor() { 
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
  }

  salvar(){
    console.log(this.usuario);
    console.log(this.usuario.id);
    console.log(this.usuario.nome);
    console.log(this.usuario.email);
    console.log(this.usuario.senha);
    console.log(this.usuario.perfil);
  }

}
