import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGuestUser } from 'app/shared/model/guest-user.model';

@Component({
    selector: 'jhi-guest-user-detail',
    templateUrl: './guest-user-detail.component.html'
})
export class GuestUserDetailComponent implements OnInit {
    guestUser: IGuestUser;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ guestUser }) => {
            this.guestUser = guestUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
