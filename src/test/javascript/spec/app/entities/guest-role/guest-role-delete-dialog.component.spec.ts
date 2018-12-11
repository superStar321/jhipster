/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { GuestRoleDeleteDialogComponent } from 'app/entities/guest-role/guest-role-delete-dialog.component';
import { GuestRoleService } from 'app/entities/guest-role/guest-role.service';

describe('Component Tests', () => {
    describe('GuestRole Management Delete Component', () => {
        let comp: GuestRoleDeleteDialogComponent;
        let fixture: ComponentFixture<GuestRoleDeleteDialogComponent>;
        let service: GuestRoleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [GuestRoleDeleteDialogComponent]
            })
                .overrideTemplate(GuestRoleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GuestRoleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GuestRoleService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
