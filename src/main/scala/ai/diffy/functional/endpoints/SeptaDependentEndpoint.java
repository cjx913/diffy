package ai.diffy.functional.endpoints;

import ai.diffy.functional.algebra.monoids.functions.SeptaOperator;

import java.util.List;

public class SeptaDependentEndpoint<
        RequestIn,
        Request1, Response1,
        Request2, Response2,
        Request3, Response3,
        Request4, Response4,
        Request5, Response5,
        Request6, Response6,
        Request7, Response7,
        ResponseOut> extends Endpoint<RequestIn, ResponseOut> {
    private final Endpoint<Request1, Response1> dependency1;
    private final Endpoint<Request2, Response2> dependency2;
    private final Endpoint<Request3, Response3> dependency3;
    private final Endpoint<Request4, Response4> dependency4;
    private final Endpoint<Request5, Response5> dependency5;
    private final Endpoint<Request6, Response6> dependency6;
    private final Endpoint<Request7, Response7> dependency7;
    private final SeptaOperator<RequestIn,
                        Request1, Response1,
                        Request2, Response2,
                        Request3, Response3,
                        Request4, Response4,
                        Request5, Response5,
                        Request6, Response6,
                        Request7, Response7,
                        ResponseOut> filter;
    protected SeptaDependentEndpoint(
            String name,
            Endpoint<Request1, Response1> dependency1,
            Endpoint<Request2, Response2> dependency2,
            Endpoint<Request3, Response3> dependency3,
            Endpoint<Request4, Response4> dependency4,
            Endpoint<Request5, Response5> dependency5,
            Endpoint<Request6, Response6> dependency6,
            Endpoint<Request7, Response7> dependency7,
            SeptaOperator<RequestIn,
                                    Request1, Response1,
                                    Request2, Response2,
                                    Request3, Response3,
                                    Request4, Response4,
                                    Request5, Response5,
                                    Request6, Response6,
                                    Request7, Response7,
                                    ResponseOut> filter) {
        super(name, filter.apply(
                dependency1,
                dependency2,
                dependency3,
                dependency4,
                dependency5,
                dependency6,
                dependency7
        ));
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
        this.dependency3 = dependency3;
        this.dependency4 = dependency4;
        this.dependency5 = dependency5;
        this.dependency6 = dependency6;
        this.dependency7 = dependency7;
        this.filter = filter;
    }

    @Override
    public List<Endpoint> getDownstream() {
        return List.of(dependency1, dependency2, dependency3, dependency4, dependency5, dependency6, dependency7);
    }

    @Override
    public Endpoint<RequestIn, ResponseOut> deepClone() {
        return new SeptaDependentEndpoint<>(
                this.name,
                dependency1.deepClone(),
                dependency2.deepClone(),
                dependency3.deepClone(),
                dependency4.deepClone(),
                dependency5.deepClone(),
                dependency6.deepClone(),
                dependency7.deepClone(),
                filter
        ).setMiddleware(this.getMiddleware());
    }

    @Override
    public Endpoint<RequestIn, ResponseOut> withDownstream(List<Endpoint> downstream) {
        assert downstream.size() == 7;
        assert this.dependency1.getClass().isAssignableFrom(downstream.get(0).getClass());
        assert this.dependency2.getClass().isAssignableFrom(downstream.get(1).getClass());
        assert this.dependency3.getClass().isAssignableFrom(downstream.get(2).getClass());
        assert this.dependency4.getClass().isAssignableFrom(downstream.get(3).getClass());
        assert this.dependency5.getClass().isAssignableFrom(downstream.get(4).getClass());
        assert this.dependency6.getClass().isAssignableFrom(downstream.get(5).getClass());
        assert this.dependency7.getClass().isAssignableFrom(downstream.get(6).getClass());
        return new SeptaDependentEndpoint<>(
                this.name,
                downstream.get(0),
                downstream.get(1),
                downstream.get(2),
                downstream.get(3),
                downstream.get(4),
                downstream.get(5),
                downstream.get(6),
                filter).setMiddleware(this.getMiddleware());
    }
}