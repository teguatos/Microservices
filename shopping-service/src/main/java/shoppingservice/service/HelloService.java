package shoppingservice.service;
import org.springframework.stereotype.Service;

import grpc.HelloReply;
import grpc.HelloRequest;
import grpc.MyServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class HelloService{

	@GrpcClient("customer-service")
	private MyServiceGrpc.MyServiceBlockingStub simpleStub;
	
    public String sendMessage(String name) {
    
        try {
        	System.out.println("sendMessage start");
        	HelloRequest request = HelloRequest.newBuilder()
    		.setName(name)
    		.build();
        	System.out.println("request constructed");
        	HelloReply response = this.simpleStub.sayHello(request);
        	System.out.println("response constructed");
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}
