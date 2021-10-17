package net.etfbl.ip.marko.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.etfbl.ip.marko.dao.AidDAO;
import net.etfbl.ip.marko.dto.Aid;

@Path("/aidservice")
public class AidService {
	
	@GET
	@Path("/aids")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAids(){
		List<Aid> retVal = new ArrayList<>();
		retVal = new AidDAO().getAids();
		if(retVal != null) {
			return Response.status(200).entity(retVal).build();
		}else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Path("/aids/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAidById(@PathParam("id") int id) {
		Aid retVal = new AidDAO().getAidById(id);
		if(retVal != null) {
			return Response.status(200).entity(retVal).build();
		}else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/aids/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAid(@PathParam("id") int id) {
		boolean retVal = new AidDAO().deleteAid(id);
		if(retVal) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@PUT
	@Path("/aids/block/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response blockAid(@PathParam("id") int id) {
		if(new AidDAO().blockAid(id)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@PUT
	@Path("/aids/report/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportAid(@PathParam("id") int id) {
		if(new AidDAO().reportAid(id)) {
			return Response.status(200).build();
		}else {
			return Response.status(404).build();
		}
	}

}
