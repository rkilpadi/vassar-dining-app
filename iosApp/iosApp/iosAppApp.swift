//
//  iosAppApp.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/7/23.
//

import SwiftUI
import shared

@main
struct iosAppApp: App {
    
    @StateObject var viewModel = MenuViewModel()
    
    var body: some Scene {
        WindowGroup {
            MainView(viewModel: viewModel).onAppear {
                Task {
                    do {
                        try await viewModel.initialize()
                    } catch {
                        // Handle any errors here
                    }
                }
            }
        }
    }
}

class MenuViewModel: ObservableObject {
    
    @Published var searching: Bool = true
    let parser = MenuParser()
    @Published var dayParts = [DayPart]()
    
    @MainActor
    func initialize() async throws {
        
        try await parser.initialize(url: "https://vassar.cafebonappetit.com/cafe/gordon/")
        
        searching = false
        
        let parsed = parser.dayParts
        
        dayParts = parsed as NSArray as! [DayPart]
    }
}
