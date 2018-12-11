import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGuestRole } from 'app/shared/model/guest-role.model';
import { GuestRoleService } from './guest-role.service';

@Component({
    selector: 'jhi-guest-role-delete-dialog',
    templateUrl: './guest-role-delete-dialog.component.html'
})
export class GuestRoleDeleteDialogComponent {
    guestRole: IGuestRole;

    constructor(private guestRoleService: GuestRoleService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.guestRoleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'guestRoleListModification',
                content: 'Deleted an guestRole'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-guest-role-delete-popup',
    template: ''
})
export class GuestRoleDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ guestRole }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GuestRoleDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.guestRole = guestRole;
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
