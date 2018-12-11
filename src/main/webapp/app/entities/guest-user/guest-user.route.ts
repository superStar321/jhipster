import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GuestUser } from 'app/shared/model/guest-user.model';
import { GuestUserService } from './guest-user.service';
import { GuestUserComponent } from './guest-user.component';
import { GuestUserDetailComponent } from './guest-user-detail.component';
import { GuestUserUpdateComponent } from './guest-user-update.component';
import { GuestUserDeletePopupComponent } from './guest-user-delete-dialog.component';
import { IGuestUser } from 'app/shared/model/guest-user.model';

@Injectable({ providedIn: 'root' })
export class GuestUserResolve implements Resolve<IGuestUser> {
    constructor(private service: GuestUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GuestUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GuestUser>) => response.ok),
                map((guestUser: HttpResponse<GuestUser>) => guestUser.body)
            );
        }
        return of(new GuestUser());
    }
}

export const guestUserRoute: Routes = [
    {
        path: 'guest-user',
        component: GuestUserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterApp.guestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guest-user/:id/view',
        component: GuestUserDetailComponent,
        resolve: {
            guestUser: GuestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guest-user/new',
        component: GuestUserUpdateComponent,
        resolve: {
            guestUser: GuestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guest-user/:id/edit',
        component: GuestUserUpdateComponent,
        resolve: {
            guestUser: GuestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const guestUserPopupRoute: Routes = [
    {
        path: 'guest-user/:id/delete',
        component: GuestUserDeletePopupComponent,
        resolve: {
            guestUser: GuestUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.guestUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
