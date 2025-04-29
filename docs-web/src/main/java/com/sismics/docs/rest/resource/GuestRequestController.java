package com.sismics.docs.rest.resource;

import com.sismics.docs.rest.resource.GuestRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Guest request REST API.
 */
@Path("/guest-request")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuestRequestController {
    // 用内存存申请（简化版）
    private static final List<GuestRequest> guestRequestList = new ArrayList<>();

    /**
     * 游客提交注册申请
     */
    @POST
    public Response submitGuestRequest(GuestRequest request) {
        guestRequestList.add(request);
        return Response.ok().build();
    }

    /**
     * 管理员查看所有PENDING的申请
     */
    @GET
    @Path("/pending")
    public Response getPendingRequests() {
        List<GuestRequest> pending = guestRequestList.stream()
                .filter(r -> "PENDING".equals(r.getStatus()))
                .collect(Collectors.toList());
        return Response.ok(pending).build();
    }

    /**
     * 管理员审批（通过或拒绝）
     */
    @POST
    @Path("/{id}/review")
    public Response reviewRequest(@PathParam("id") String id, @FormParam("action") String action) {
        for (GuestRequest request : guestRequestList) {
            if (request.getId().equals(id)) {
                if ("approve".equalsIgnoreCase(action)) {
                    request.setStatus("APPROVED");
                } else if ("reject".equalsIgnoreCase(action)) {
                    request.setStatus("REJECTED");
                }
                request.setReviewDate(new java.util.Date());
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
