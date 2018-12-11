/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { GuestUserUpdateComponent } from 'app/entities/guest-user/guest-user-update.component';
import { GuestUserService } from 'app/entities/guest-user/guest-user.service';
import { GuestUser } from 'app/shared/model/guest-user.model';

describe('Component Tests', () => {
    describe('GuestUser Management Update Component', () => {
        let comp: GuestUserUpdateComponent;
        let fixture: ComponentFixture<GuestUserUpdateComponent>;
        let service: GuestUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [GuestUserUpdateComponent]
            })
                .overrideTemplate(GuestUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GuestUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GuestUserService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new GuestUser(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.guestUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new GuestUser();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.guestUser = entity;
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
