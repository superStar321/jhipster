/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { GRoleDetailComponent } from 'app/entities/g-role/g-role-detail.component';
import { GRole } from 'app/shared/model/g-role.model';

describe('Component Tests', () => {
    describe('GRole Management Detail Component', () => {
        let comp: GRoleDetailComponent;
        let fixture: ComponentFixture<GRoleDetailComponent>;
        const route = ({ data: of({ gRole: new GRole(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [GRoleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GRoleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GRoleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.gRole).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
