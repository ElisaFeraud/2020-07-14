package it.polito.tdp.PremierLeague.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.PremierLeague.model.Action;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Player;
import it.polito.tdp.PremierLeague.model.Team;

public class PremierLeagueDAO {
	
	public List<Player> listAllPlayers(){
		String sql = "SELECT * FROM Players";
		List<Player> result = new ArrayList<Player>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Player player = new Player(res.getInt("PlayerID"), res.getString("Name"));
				
				result.add(player);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void listAllTeams(Map<Integer,Team> idMap){
		String sql = "SELECT * FROM Teams";
	
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
              if(!idMap.containsKey(res.getInt("TeamID"))) {
				Team team = new Team(res.getInt("TeamID"), res.getString("Name"));
				idMap.put(res.getInt("TeamID"), team);}
				
			}
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	public List<Team> getAllTeams(){
		String sql = "SELECT * FROM Teams";
	
		Connection conn = DBConnect.getConnection();
         List<Team> result = new LinkedList<>();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
             
				Team team = new Team(res.getInt("TeamID"), res.getString("Name"));
				result.add(team);
				}
				
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<Action> listAllActions(){
		String sql = "SELECT * FROM Actions";
		List<Action> result = new ArrayList<Action>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Action action = new Action(res.getInt("PlayerID"),res.getInt("MatchID"),res.getInt("TeamID"),res.getInt("Starts"),res.getInt("Goals"),
						res.getInt("TimePlayed"),res.getInt("RedCards"),res.getInt("YellowCards"),res.getInt("TotalSuccessfulPassesAll"),res.getInt("totalUnsuccessfulPassesAll"),
						res.getInt("Assists"),res.getInt("TotalFoulsConceded"),res.getInt("Offsides"));
				
				result.add(action);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Match> listAllMatches(){
		String sql = "SELECT m.MatchID, m.TeamHomeID, m.TeamAwayID, m.teamHomeFormation, m.teamAwayFormation, m.resultOfTeamHome, m.date, t1.Name, t2.Name   "
				+ "FROM Matches m, Teams t1, Teams t2 "
				+ "WHERE m.TeamHomeID = t1.TeamID AND m.TeamAwayID = t2.TeamID";
		List<Match> result = new ArrayList<Match>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				
				Match match = new Match(res.getInt("m.MatchID"), res.getInt("m.TeamHomeID"), res.getInt("m.TeamAwayID"), res.getInt("m.teamHomeFormation"), 
							res.getInt("m.teamAwayFormation"),res.getInt("m.resultOfTeamHome"), res.getTimestamp("m.date").toLocalDateTime(), res.getString("t1.Name"),res.getString("t2.Name"));
				
				
				result.add(match);

			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void addVittorie(Map<Integer,Team> idMap) {
		String sql = "SELECT TeamHomeID AS t1, SUM(ResultOfTeamHome*3) AS r "
				+ "FROM matches "
				+ "WHERE ResultOfTeamHome='+1' "
				+ "GROUP BY TeamHomeID ";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
              if(idMap.containsKey(res.getInt("t1"))) {
				Team team = idMap.get(res.getInt("t1"));
				int vittorie = res.getInt("r");
				team.setVittorie(vittorie);
              }
				
			}
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	/*public void addSconfitta(Map<Integer,Team> idMap) {
		String sql = "SELECT TeamHomeID AS t1, SUM(ResultOfTeamHome) AS r "
				+ "FROM matches "
				+ "WHERE ResultOfTeamHome=-1 "
				+ "GROUP BY TeamHomeID ";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
              if(idMap.containsKey(res.getInt("t1"))) {
				Team team = idMap.get(res.getInt("t1"));
				int sconfitte = res.getInt("r");
				team.setSconfitte(sconfitte);
              }
				
			}
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}*/
	public void addPareggio(Map<Integer,Team> idMap) {
		String sql = "SELECT TeamHomeID AS t1, COUNT(ResultOfTeamHome) AS r "
				+ "FROM matches "
				+ "WHERE ResultOfTeamHome='0' "
				+ "GROUP BY TeamHomeID ";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
              if(idMap.containsKey(res.getInt("t1"))) {
				Team team = idMap.get(res.getInt("t1"));
				int pareggi = res.getInt("r");
				team.setPareggio(pareggi);
              }
				
			}
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void getVittorieFuori(Map<Integer,Team> idMap) {
		String sql = "SELECT TeamAwayID AS t, SUM(ABS(ResultOfTeamHome)*3) AS r "
				+ "FROM matches "
				+ "WHERE ResultOfTeamHome='-1' "
				+ "GROUP BY t ";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
              if(idMap.containsKey(res.getInt("t"))) {
				Team team = idMap.get(res.getInt("t"));
				int vittorieFuoriCasa = res.getInt("r");
				team.setVittorieFuoriCasa(Math.abs(vittorieFuoriCasa));
              }
				
			}
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	public void addPareggioFuori(Map<Integer,Team> idMap) {
		String sql = "SELECT TeamAwayID AS t1, COUNT(ResultOfTeamHome) AS r "
				+ "FROM matches "
				+ "WHERE ResultOfTeamHome='0' "
				+ "GROUP BY t1";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
              if(idMap.containsKey(res.getInt("t1"))) {
				Team team = idMap.get(res.getInt("t1"));
				int pareggi = res.getInt("r");
				team.setPareggioFuori(pareggi);
              }
				
			}
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

}
