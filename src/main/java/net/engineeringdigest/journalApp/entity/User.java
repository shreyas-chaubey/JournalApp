package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document (collection = "user")               //mapped entry of mongodb collection. INSTANCE OF JournalEntry will be equal to document(row)
@Data
@NoArgsConstructor
public class User {


    @Id
    private ObjectId id;

    @Indexed(unique=true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @DBRef
    public List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;



}
