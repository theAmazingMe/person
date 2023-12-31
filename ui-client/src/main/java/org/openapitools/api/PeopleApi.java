/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.openapitools.model.Person;
import org.openapitools.model.ProposePerson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-30T15:10:42.946137100+02:00[Europe/Brussels]")
@Validated
@Tag(name = "people", description = "the people API")
public interface PeopleApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /people
     * Finds all people listed
     *
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "peopleGet",
        description = "Finds all people listed",
        tags = { "people" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/people",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Person>> peopleGet(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"birthday\" : \"{}\", \"firstname\" : \"firstname\", \"id\" : 0, \"lastname\" : \"lastname\" }, { \"birthday\" : \"{}\", \"firstname\" : \"firstname\", \"id\" : 0, \"lastname\" : \"lastname\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        final String uri = "http://localhost:8080/people";

        RestTemplate restTemplate = new RestTemplate();
        return ResponseEntity.ok(restTemplate.getForObject(uri, List.class));

    }


    /**
     * GET /people/{id}
     * Finds a person by it&#39;s ID
     *
     * @param id ID of person to return (required)
     * @return successful operation (status code 200)
     *         or Person not found (status code 404)
     */
    @Operation(
        operationId = "peopleIdGet",
        description = "Finds a person by it's ID",
        tags = { "people" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            }),
            @ApiResponse(responseCode = "404", description = "Person not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/people/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Person> peopleIdGet(
        @Parameter(name = "id", description = "ID of person to return", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"birthday\" : \"{}\", \"firstname\" : \"firstname\", \"id\" : 0, \"lastname\" : \"lastname\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        final String uri = "http://localhost:8080/people/"+id;

        RestTemplate restTemplate = new RestTemplate();
        return ResponseEntity.ok(restTemplate.getForObject(uri, Person.class));

    }


    /**
     * POST /people
     * Finds all people listed
     *
     * @param firstname firstname of the person (optional)
     * @param lastname Status of pet that needs to be updated (optional)
     * @param birthday  (optional)
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "peoplePost",
        description = "Finds all people listed",
        tags = { "people" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/people"
    )
    default ResponseEntity<Void> peoplePost(
        @Parameter(name = "firstname", description = "firstname of the person", in = ParameterIn.QUERY) @Valid @RequestParam(value = "firstname", required = false) String firstname,
        @Parameter(name = "lastname", description = "Status of pet that needs to be updated", in = ParameterIn.QUERY) @Valid @RequestParam(value = "lastname", required = false) String lastname,
        @Parameter(name = "birthday", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "birthday", required = false) String birthday
    ) {
        final String uri = "http://localhost:8080/people";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, new ProposePerson(firstname,lastname, LocalDateTime.parse(birthday+"T00:00:00").toLocalDate()), ProposePerson.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
