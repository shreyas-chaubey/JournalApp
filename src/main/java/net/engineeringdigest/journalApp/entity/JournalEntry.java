package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document (collection = "journal_entries")               //mapped entry of mongodb collection. INSTANCE OF JournalEntry will be equal to document(row)
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id           // unique key
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;


}
