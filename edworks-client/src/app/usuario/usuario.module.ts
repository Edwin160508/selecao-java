import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';


@NgModule({
  declarations: [UsuarioFormComponent],
  imports: [
    CommonModule,
    UsuarioRoutingModule
  ],
  exports:[UsuarioFormComponent]
})
export class UsuarioModule { }
