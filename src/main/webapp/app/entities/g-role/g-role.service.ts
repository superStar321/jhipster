import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGRole } from 'app/shared/model/g-role.model';

type EntityResponseType = HttpResponse<IGRole>;
type EntityArrayResponseType = HttpResponse<IGRole[]>;

@Injectable({ providedIn: 'root' })
export class GRoleService {
    public resourceUrl = SERVER_API_URL + 'api/g-roles';

    constructor(private http: HttpClient) {}

    create(gRole: IGRole): Observable<EntityResponseType> {
        return this.http.post<IGRole>(this.resourceUrl, gRole, { observe: 'response' });
    }

    update(gRole: IGRole): Observable<EntityResponseType> {
        return this.http.put<IGRole>(this.resourceUrl, gRole, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGRole>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGRole[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
