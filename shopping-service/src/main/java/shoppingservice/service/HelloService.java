package shoppingservice.service;
import org.springframework.stereotype.Service;

import grpc.HelloReply;
import grpc.HelloRequest;
import grpc.MyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

@Service
public class HelloService{

	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();

	//	@GrpcClient("customer-service"))
	private MyServiceGrpc.MyServiceBlockingStub simpleStub = MyServiceGrpc.newBlockingStub(channel);
	
	public String sendMessage(String name) {

    	System.out.println("sendMessage start");
    	
    	HelloRequest request = HelloRequest.newBuilder()
        		.setName(name)
        		.build();
        try {
        	System.out.println("request constructed");
        	HelloReply response = simpleStub.sayHello(request);
        	System.out.println("response constructed");
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
	
}
