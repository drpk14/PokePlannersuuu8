package ramos.david.pokeplanner.RoomDataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//
@Entity(tableName = "team_table")
public class Team {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String teamName;

    @ColumnInfo(name ="pokemon1")
    private int pokemon1;

    @ColumnInfo(name ="pokemon2")
    private int pokemon2;

    @ColumnInfo(name ="pokemon3")
    private int pokemon3;

    @ColumnInfo(name ="pokemon4")
    private int pokemon4;

    @ColumnInfo(name ="pokemon5")
    private int pokemon5;
    @ColumnInfo(name ="pokemon6")
    private int pokemon6;

    public Team(@NonNull String teamName) {
        this.teamName = teamName;
        pokemon1 = 0;
        pokemon2 = 0;
        pokemon3 = 0;
        pokemon4 = 0;
        pokemon5 = 0;
        pokemon6 = 0;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(int pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public int getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(int pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public int getPokemon3() {
        return pokemon3;
    }

    public void setPokemon3(int pokemon3) {
        this.pokemon3 = pokemon3;
    }

    public int getPokemon4() {
        return pokemon4;
    }

    public void setPokemon4(int pokemon4) {
        this.pokemon4 = pokemon4;
    }

    public int getPokemon5() {
        return pokemon5;
    }

    public void setPokemon5(int pokemon5) {
        this.pokemon5 = pokemon5;
    }

    public int getPokemon6() {
        return pokemon6;
    }

    public void setPokemon6(int pokemon6) {
        this.pokemon6 = pokemon6;
    }
}
