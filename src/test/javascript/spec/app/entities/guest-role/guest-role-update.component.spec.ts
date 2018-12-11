/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { GuestRoleUpdateComponent } from 'app/entities/guest-role/guest-role-update.component';
import { GuestRoleService } from 'app/entities/guest-role/guest-role.service';
import { GuestRole } from 'app/shared/model/guest-role.model';

describe('Component Tests', () => {
    describe('GuestRole Management Update Component', () => {
        let comp: GuestRoleUpdateComponent;
        let fixture: ComponentFixture<GuestRoleUpdateComponent>;
        let service: GuestRoleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [GuestRoleUpdateComponent]
            })
                .overrideTemplate(GuestRoleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GuestRoleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GuestRoleService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new GuestRole(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.guestRole = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new GuestRole();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.guestRole = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
