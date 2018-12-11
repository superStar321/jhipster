import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GRole } from 'app/shared/model/g-role.model';
import { GRoleService } from './g-role.service';
import { GRoleComponent } from './g-role.component';
import { GRoleDetailComponent } from './g-role-detail.component';
import { GRoleUpdateComponent } from './g-role-update.component';
import { GRoleDeletePopupComponent } from './g-role-delete-dialog.component';
import { IGRole } from 'app/shared/model/g-role.model';

@Injectable({ providedIn: 'root' })
export class GRoleResolve implements Resolve<IGRole> {
    constructor(private service: GRoleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GRole> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GRole>) => response.ok),
                map((gRole: HttpResponse<GRole>) => gRole.body)
            );
        }
        return of(new GRole());
    }
}

export const gRoleRoute: Routes = [
    {
        path: 'g-role',
        component: GRoleComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterApp.gRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'g-role/:id/view',
        component: GRoleDetailComponent,
        resolve: {
            gRole: GRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.gRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'g-role/new',
        component: GRoleUpdateComponent,
        resolve: {
            gRole: GRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.gRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'g-role/:id/edit',
        component: GRoleUpdateComponent,
        resolve: {
            gRole: GRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.gRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const gRolePopupRoute: Routes = [
    {
        path: 'g-role/:id/delete',
        component: GRoleDeletePopupComponent,
        resolve: {
            gRole: GRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.gRole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
