package controller;

import model.DBConnection;
import model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialController {

    public List<Material> getMaterialsByCourseId(int courseId) {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials WHERE course_id = ?";

        try (
                Connection conn = DBConnection.getInstance().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Material material = new Material(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("filePath"), // or "content" if that's the correct column
                        rs.getString("type")
                );
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }
}
