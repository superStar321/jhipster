/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { GRoleUpdateComponent } from 'app/entities/g-role/g-role-update.component';
import { GRoleService } from 'app/entities/g-role/g-role.service';
import { GRole } from 'app/shared/model/g-role.model';

describe('Component Tests', () => {
    describe('GRole Management Update Component', () => {
        let comp: GRoleUpdateComponent;
        let fixture: ComponentFixture<GRoleUpdateComponent>;
        let service: GRoleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [GRoleUpdateComponent]
            })
                .overrideTemplate(GRoleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GRoleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GRoleService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new GRole(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.gRole = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new GRole();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.gRole = entity;
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
