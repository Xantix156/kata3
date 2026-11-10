package software.ulpgc.katas.model;

import java.util.List;

public record UserExport(List<User> users, int discardedRecords) {
}
