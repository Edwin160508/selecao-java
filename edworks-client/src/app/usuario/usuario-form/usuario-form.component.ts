import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  usuario: Usuario;
  constructor(private service:UsuarioService) { 
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
  }

  salvar(){
    this.service
    .salvar(this.usuario)
    .subscribe(response => {
      console.log(response);
    });
  }

}
