package controllers;

import api.ReceiptResponse;
import dao.*;
import generated.tables.records.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;
    final ReceiptDao receipts;

    public TagController(TagDao tags, ReceiptDao receipts) {
        this.tags = tags;
        this.receipts = receipts;
    }

    @PUT
    @Path("/{tag}")
    public void toggleTag(@PathParam("tag") String tagName, int id) {
        tags.insert(tagName, id);
    }

    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getTags(@PathParam("tag") String tagName) {
        List<Integer> receiptIDs = tags.getAllReceiptsFORTags(tagName);
        List<ReceiptsRecord> ReleventReceipts = receipts.getReleventReceipts(receiptIDs);
        return ReleventReceipts.stream().map(ReceiptResponse::new).collect(toList());
    }


}
