package com.football.manager.entity.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.football.manager.entity.bill.BillRepresentDTO;
import com.football.manager.entity.team.Team;
import com.football.manager.entity.transaction.TransactionRepresentDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class TeamHistoryDTO {
    private int id;
    @JsonProperty("club_name")
    private String clubName;
    @JsonProperty("club_commission")
    private float commission;

    private float budget;
    @JsonProperty("bills_to_pay")
    private List<BillRepresentDTO> bills;
    @JsonProperty("transactions_paid")
    private List<TransactionRepresentDTO> transactions;

    @JsonProperty("bills_count")
    private int billsCount;
    @JsonProperty("transactions_count")
    private int transactionsCount;

    @JsonProperty("players_count")
    private int playersCount;

    public static TeamHistoryDTO from(Team team) {
        return builder()
                .id(team.getId())
                .clubName(team.getClubName())
                .commission(team.getCommission())
                .budget(team.getBudget())
                .bills(team.getBills().stream().map(BillRepresentDTO::from).collect(Collectors.toList()))
                .transactions(team.getTransactions().stream().map(TransactionRepresentDTO::from).collect(Collectors.toList()))
                .billsCount(team.getBills().size())
                .transactionsCount(team.getTransactions().size())
                .playersCount(team.getPlayers().size())
                .build();
    }
}
