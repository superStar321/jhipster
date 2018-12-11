import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGuestUser } from 'app/shared/model/guest-user.model';

type EntityResponseType = HttpResponse<IGuestUser>;
type EntityArrayResponseType = HttpResponse<IGuestUser[]>;

@Injectable({ providedIn: 'root' })
export class GuestUserService {
    public resourceUrl = SERVER_API_URL + 'api/guest-users';

    constructor(private http: HttpClient) {}

    create(guestUser: IGuestUser): Observable<EntityResponseType> {
        return this.http.post<IGuestUser>(this.resourceUrl, guestUser, { observe: 'response' });
    }

    update(guestUser: IGuestUser): Observable<EntityResponseType> {
        return this.http.put<IGuestUser>(this.resourceUrl, guestUser, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGuestUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGuestUser[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
