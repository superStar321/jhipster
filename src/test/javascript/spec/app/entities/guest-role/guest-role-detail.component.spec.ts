/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { GuestRoleDetailComponent } from 'app/entities/guest-role/guest-role-detail.component';
import { GuestRole } from 'app/shared/model/guest-role.model';

describe('Component Tests', () => {
    describe('GuestRole Management Detail Component', () => {
        let comp: GuestRoleDetailComponent;
        let fixture: ComponentFixture<GuestRoleDetailComponent>;
        const route = ({ data: of({ guestRole: new GuestRole(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [GuestRoleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GuestRoleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GuestRoleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.guestRole).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
