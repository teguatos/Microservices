package costumerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import costumerservice.Repository.entity.Customer;
import grpc.HelloReply;
import grpc.HelloRequest;
import grpc.MyServiceGrpc.MyServiceImplBase;
import grpc.getCustomerByIdRequest;
import grpc.getCustomerByIdResponse;
import grpc.getCustomersRequest;
import grpc.getCustomersResponse;
import grpc.getCustomersResponse.Builder;
import grpc.region;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends MyServiceImplBase{

    @Autowired
    CustomerService customerService;
    
	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver)  {
		HelloReply response = HelloReply.newBuilder()
                .setMessage(request.getName())
                .build();
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
	
	@Override
	public void getCustomerById(getCustomerByIdRequest request, StreamObserver<getCustomerByIdResponse> responseObserver)  {
		Customer customer = customerService.getCustomer(request.getId());
		getCustomerByIdResponse response = getCustomerByIdResponse.newBuilder()
                .setId(customer.getId())
                .setNumerId(customer.getNumberID())
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setPhotoUrl(customer.getPhotoUrl())
                .setRegion(region.newBuilder()
                		.setId(customer.getRegion().getId())
                		.setName(customer.getRegion().getName())
                		.build())
                .setState(customer.getState())
                .build();
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
	
	@Override
	public void getCustomers(getCustomersRequest request, StreamObserver<getCustomersResponse> responseObserver)  {
		
		List<Customer> customers = customerService.findCustomerAll();
		Builder BuildResponse = getCustomersResponse.newBuilder();
		
		for (Customer customer : customers) 
		{ 
			BuildResponse.addCustomers(getCustomerByIdResponse.newBuilder()
	                .setId(customer.getId())
	                .setNumerId(customer.getNumberID())
	                .setFirstName(customer.getFirstName())
	                .setLastName(customer.getLastName())
	                .setEmail(customer.getEmail())
	                .setPhotoUrl(customer.getPhotoUrl())
	                .setRegion(region.newBuilder()
	                		.setId(customer.getRegion().getId())
	                		.setName(customer.getRegion().getName())
	                		.build())
	                .setState(customer.getState())
	                .build());
		}
		getCustomersResponse response = BuildResponse.build();
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
	
}
