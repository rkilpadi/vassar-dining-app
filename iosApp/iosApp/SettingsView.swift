//
//  SettingsView.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/12/23.
//

import SwiftUI
import shared

struct SettingsView: View {
    
    @Binding var presentSideMenu: Bool
    @StateObject var viewModel: MenuViewModel
    @StateObject var user: User
    @Binding var selectedSideMenuTab: Int
    
    var body: some View {
        VStack(spacing: 0) {
            HStack{
                Button{
                    presentSideMenu.toggle()
                } label: {
                    Image("Menu")
                        .resizable()
                        .frame(width: 32, height: 32)
                }
                .padding(.trailing)
                Text("Settings").bold()
                Spacer()
            }.padding().background(Color("purple_700"))
            
            List {
                Picker("Schools", selection: $user.school) {
                    ForEach(viewModel.schools, id: \.self) { school in
                        Text(school.uppercased())
                    }
                }
                .onChange(of: user.school) { _ in
                    user.saveToStorage()
                    viewModel.selectedCafe = 0
                    Task {
                        do {
                            try await viewModel.initialize(user: user)
                        } catch MyError.runtimeError(let errorMessage) {
                            viewModel.error = errorMessage
                        }
                    }
                    selectedSideMenuTab = 0
                    viewModel.searching = true
                    viewModel.error = ""
                }
                .padding()
                Picker("Cafe", selection: $viewModel.selectedCafe) {
                    ForEach(viewModel.cafes.indices, id: \.self) { i in
                        Text(viewModel.cafes[i]
                            .replacingOccurrences(of: "-", with: " ")
                            .uppercased())
                    }
                }
                .padding()
                DatePicker("Date", selection: $viewModel.date, in: Date.now..., displayedComponents: .date)
                    .padding()
                HStack {
                    Spacer()
                    Button {
                        Task {
                            do {
                                try await viewModel.initialize(user: user)
                            } catch MyError.runtimeError(let errorMessage) {
                                viewModel.error = errorMessage
                            }
                        }
                        selectedSideMenuTab = 0
                        viewModel.error = ""
                        viewModel.searching = true
                    } label: {
                        Text("Enter")
                    }
                    Spacer()
                }
            }
            Spacer()
        }
    }
}
