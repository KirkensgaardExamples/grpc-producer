package kirkensgaard.examples;

import io.quarkus.example.Empty;
import io.quarkus.example.HelloReply;
import io.quarkus.example.HelloRequest;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@GrpcService
public class GreetingsService implements io.quarkus.example.Greeter {

    List<String> names = new ArrayList<>();
    Random random = new Random();

    GreetingsService(){
        names.add("Mark");
        names.add("David");
        names.add("Erik");
        names.add("Niels");
        names.add("Peter");
    }

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return null;
    }

    @Override
    public Multi<HelloRequest> source(Empty request) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(2))
                .select().first(100)
                .map(l -> HelloRequest.newBuilder().setName(names.get(random.nextInt(5))).build());
    }
}
