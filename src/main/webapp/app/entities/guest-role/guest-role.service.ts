import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGuestRole } from 'app/shared/model/guest-role.model';

type EntityResponseType = HttpResponse<IGuestRole>;
type EntityArrayResponseType = HttpResponse<IGuestRole[]>;

@Injectable({ providedIn: 'root' })
export class GuestRoleService {
    public resourceUrl = SERVER_API_URL + 'api/guest-roles';

    constructor(private http: HttpClient) {}

    create(guestRole: IGuestRole): Observable<EntityResponseType> {
        return this.http.post<IGuestRole>(this.resourceUrl, guestRole, { observe: 'response' });
    }

    update(guestRole: IGuestRole): Observable<EntityResponseType> {
        return this.http.put<IGuestRole>(this.resourceUrl, guestRole, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGuestRole>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGuestRole[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
