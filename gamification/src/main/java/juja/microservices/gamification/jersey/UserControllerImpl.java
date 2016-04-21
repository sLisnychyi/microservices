/**
 * Copyright (c) 2016, juja.com.ua
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * * Neither the name of microservices nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package juja.microservices.gamification.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import juja.microservices.gamification.model.entity.User;
import juja.microservices.gamification.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Rest requests to operate with users
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
@Component
@Path("/user")
public class UserControllerImpl {

    /**
     * Service.
     */
    @Autowired
    private Service service;

    /**
     * Create user.
     * @param username username
     */
    @GET
    @Path("/create/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public final String createUser(@PathParam("username") final String username) {
        return this.service.createUser(username);
    }

    /**
     * Get user by uuid.
     * @param uuid uuid
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{UUID}")
    public final Response getUser( @PathParam("UUID") final String uuid) {
        final User user = this.service.getUser(uuid);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Can't find user {%S} uuid", uuid))
                    .build();
        }
    }

    /**
     * Get list of all users.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public final List<User> getUsers() {
        return this.service.getUsers();
    }

}
