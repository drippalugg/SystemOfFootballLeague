package com.example.sukablyat.controller;

import com.example.sukablyat.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import java.time.LocalDate;

public class MainController {
    private ObservableList<Team> teams = FXCollections.observableArrayList();
    private ObservableList<Match> matches = FXCollections.observableArrayList();

    @FXML private ListView<Team> teamsListView;
    @FXML private ListView<Player> playersListView;
    @FXML private ListView<Match> matchesListView;
    @FXML private TextField teamNameField;
    @FXML private TextField teamCityField;
    @FXML private ComboBox<Team> homeTeamCombo;
    @FXML private ComboBox<Team> awayTeamCombo;
    @FXML private DatePicker matchDatePicker;

    @FXML
    public void initialize() {
        // Инициализация команд
        teamsListView.setItems(teams);

        // Инициализация матчей
        matchesListView.setItems(matches);

        // Заполнение ComboBox для выбора команд
        homeTeamCombo.setItems(teams);
        awayTeamCombo.setItems(teams);

        // Тестовые данные
        Team team1 = new Team("Динамо", "Москва");
        team1.addPlayer(new Player("Иванов", "Нападающий", 10));
        teams.add(team1);

        Team team2 = new Team("Спартак", "Москва");
        team2.addPlayer(new Player("Петров", "Защитник", 5));
        teams.add(team2);
    }

    @FXML
    private void onAddTeam() {
        String name = teamNameField.getText().trim();
        String city = teamCityField.getText().trim();

        if (!name.isEmpty() && !city.isEmpty()) {
            teams.add(new Team(name, city));
            teamNameField.clear();
            teamCityField.clear();
        }
    }

    @FXML
    private void onAddMatch() {
        Team home = homeTeamCombo.getValue();
        Team away = awayTeamCombo.getValue();
        LocalDate date = matchDatePicker.getValue();

        if (home != null && away != null && date != null && !home.equals(away)) {
            matches.add(new Match(home, away, date));
            homeTeamCombo.setValue(null);
            awayTeamCombo.setValue(null);
            matchDatePicker.setValue(null);
        }
    }
}