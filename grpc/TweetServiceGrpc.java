package com.cit.data.grcp;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: data_access.proto")
public final class TweetServiceGrpc {

  private TweetServiceGrpc() {}

  public static final String SERVICE_NAME = "com.cit.data.grcp.TweetService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.cit.data.grcp.TweetRequest,
      com.cit.data.grcp.TweetResponse> METHOD_REQUEST =
      io.grpc.MethodDescriptor.<com.cit.data.grcp.TweetRequest, com.cit.data.grcp.TweetResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "com.cit.data.grcp.TweetService", "request"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cit.data.grcp.TweetRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cit.data.grcp.TweetResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TweetServiceStub newStub(io.grpc.Channel channel) {
    return new TweetServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TweetServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TweetServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TweetServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TweetServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TweetServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void request(com.cit.data.grcp.TweetRequest request,
        io.grpc.stub.StreamObserver<com.cit.data.grcp.TweetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REQUEST, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REQUEST,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.cit.data.grcp.TweetRequest,
                com.cit.data.grcp.TweetResponse>(
                  this, METHODID_REQUEST)))
          .build();
    }
  }

  /**
   */
  public static final class TweetServiceStub extends io.grpc.stub.AbstractStub<TweetServiceStub> {
    private TweetServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TweetServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TweetServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TweetServiceStub(channel, callOptions);
    }

    /**
     */
    public void request(com.cit.data.grcp.TweetRequest request,
        io.grpc.stub.StreamObserver<com.cit.data.grcp.TweetResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_REQUEST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TweetServiceBlockingStub extends io.grpc.stub.AbstractStub<TweetServiceBlockingStub> {
    private TweetServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TweetServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TweetServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TweetServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.cit.data.grcp.TweetResponse> request(
        com.cit.data.grcp.TweetRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_REQUEST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TweetServiceFutureStub extends io.grpc.stub.AbstractStub<TweetServiceFutureStub> {
    private TweetServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TweetServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TweetServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TweetServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REQUEST = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TweetServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TweetServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST:
          serviceImpl.request((com.cit.data.grcp.TweetRequest) request,
              (io.grpc.stub.StreamObserver<com.cit.data.grcp.TweetResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class TweetServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cit.data.grcp.DataAccess.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TweetServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TweetServiceDescriptorSupplier())
              .addMethod(METHOD_REQUEST)
              .build();
        }
      }
    }
    return result;
  }
}
