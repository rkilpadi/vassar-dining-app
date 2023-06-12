//
//  MenuViewModel.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/12/23.
//

import SwiftUI
import shared

class MenuViewModel: ObservableObject {
    
    @Published var searching: Bool = true
    @Published var dayParts: [DayPart] = [DayPart]()
    @Published var error = ""
    @Published var date = Date.now
    @Published var cafes: [String] = []
    @Published var selectedCafe: Int = 0
    @Published var schools: [String] = []
    
    func loadSchools() {
        let parser = MenuParser()
        schools = parser.schools
    }
    
    
    @MainActor
    func initialize(user: User) async throws {
        error = ""
        
        var parser = MenuParser()
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let formattedDate = dateFormatter.string(from: date)
        
        try await parser.initialize(url: "https://\(user.school).cafebonappetit.com")
        
        cafes = (parser.cafes as NSSet).allObjects as NSArray as! [String]
        
        if cafes.isEmpty {
            throw MyError.runtimeError("No cafes found")
        }
        
        parser = MenuParser()
        dayParts = []
        
        if (cafes[selectedCafe] == "All Cafes") {
            try await parser.initialize(url: "https://\(user.school).cafebonappetit.com/cafe/\(formattedDate)/")
        } else {
            try await parser.initialize(url: "https://\(user.school).cafebonappetit.com/cafe/\(cafes[selectedCafe])/\(formattedDate)/")
        }
        
        let parsed = parser.dayParts
        
        dayParts = parsed as NSArray as! [DayPart]
        
        if dayParts.isEmpty {
            throw MyError.runtimeError("No menu was found at current location/date.\nPlease try again.")
        }
        
        searching = false
    }
}

enum MyError: Error {
    case runtimeError(String)
}
