package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.GameController;
import de.niklaseckert.reviewbombedapi.controller.UserController;
import de.niklaseckert.reviewbombedapi.model.DiaryEntry;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiaryEntryModelAssembler implements RepresentationModelAssembler<DiaryEntry, EntityModel<DiaryEntry>> {

    @NonNull
    @Override
    public EntityModel<DiaryEntry> toModel(@NonNull DiaryEntry diaryEntry) {
        return EntityModel.of(diaryEntry,
                    linkTo(methodOn(UserController.class).oneDiaryEntry(diaryEntry.getUser().getId(), diaryEntry.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).diaryOfUser(diaryEntry.getUser().getId())).withRel("diary"),
                    linkTo(methodOn(UserController.class).one(diaryEntry.getUser().getId())).withRel("user"),
                    linkTo(methodOn(GameController.class).one(diaryEntry.getGame().getId())).withRel("game")
                );
    }
}
