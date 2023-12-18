package com.dealership.demo.model.repositories;

import com.dealership.demo.model.services.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
    public class DealershipRepository {
        private final DataSource dataSource;

        @Autowired
        public DealershipRepository(DataSource dataSource){
            this.dataSource = dataSource;
        }

        //CRUD functions for dealership
        //getAllDealerships, getDealershipById, createDealership, updateDealership, deleteDealership

    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dealerships.add(new Dealership(
                        rs.getInt("dealershipId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    public Dealership getDealershipByID(int dealershipId) {
        Dealership dealership = null;
        String query = "SELECT * FROM dealerships WHERE dealershipId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, dealershipId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dealership = new Dealership(
                        rs.getInt("dealershipId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealership;
    }

    public void createDealership(Dealership dealership) {
        String query = "INSERT INTO dealerships (name, address, phone) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dealership.getName());
            ps.setString(2, dealership.getAddress());
            ps.setString(3, dealership.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDealership(Dealership dealership) {
        String query = "UPDATE dealerships SET name = ?, address = ?, phone = ? WHERE dealershipId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dealership.getName());
            ps.setString(2, dealership.getAddress());
            ps.setString(3, dealership.getPhone());
            ps.setInt(4, dealership.getDealershipId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDealership(int dealershipId) {
        String query = "DELETE FROM dealerships WHERE dealershipId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, dealershipId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





