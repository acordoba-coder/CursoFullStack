import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TomadorModule } from '../models/tomador/tomador.module';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class TomadorServiceService {

  private apiURL = 'http://localhost:9090/api/tomadores'

  constructor(private http: HttpClient) { }

  getTomadores(): Observable<TomadorModule[]> {
     return this.http.get<TomadorModule[]>(this.apiURL);
  }

  crearTomador(tomador: TomadorModule): Observable<TomadorModule> {
    console.log(tomador);
    return this.http.post<TomadorModule>(this.apiURL,tomador);
  }

  updateTomador(cctomador: number, tomador:TomadorModule): Observable<TomadorModule> {
    return this.http.put<TomadorModule>(`${this.apiURL}/${cctomador}`,tomador);
  }

  deleteTomador(tomador: TomadorModule): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${tomador.ccTomador}`);
  }

}
