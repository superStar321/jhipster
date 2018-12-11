import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGuestUser } from 'app/shared/model/guest-user.model';
import { GuestUserService } from './guest-user.service';

@Component({
    selector: 'jhi-guest-user-delete-dialog',
    templateUrl: './guest-user-delete-dialog.component.html'
})
export class GuestUserDeleteDialogComponent {
    guestUser: IGuestUser;

    constructor(private guestUserService: GuestUserService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.guestUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'guestUserListModification',
                content: 'Deleted an guestUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-guest-user-delete-popup',
    template: ''
})
export class GuestUserDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ guestUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GuestUserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.guestUser = guestUser;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
