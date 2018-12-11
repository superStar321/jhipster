import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGuestUser } from 'app/shared/model/guest-user.model';
import { GuestUserService } from './guest-user.service';

@Component({
    selector: 'jhi-guest-user-update',
    templateUrl: './guest-user-update.component.html'
})
export class GuestUserUpdateComponent implements OnInit {
    guestUser: IGuestUser;
    isSaving: boolean;

    constructor(private guestUserService: GuestUserService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ guestUser }) => {
            this.guestUser = guestUser;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.guestUser.id !== undefined) {
            this.subscribeToSaveResponse(this.guestUserService.update(this.guestUser));
        } else {
            this.subscribeToSaveResponse(this.guestUserService.create(this.guestUser));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGuestUser>>) {
        result.subscribe((res: HttpResponse<IGuestUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
