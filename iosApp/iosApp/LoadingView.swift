//
//  LoadingView.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/10/23.
//

import SwiftUI

struct LoadingView: View {
    @StateObject var viewModel: MenuViewModel
    @StateObject var user: User
    
    var body: some View {
        VStack (spacing: 0) {
            
            if viewModel.error == "" {
                Text("Loading, Please Wait")
            } else {
                
                HStack (alignment: .center, spacing: 20) {
                    Spacer()
                    Text(viewModel.error)
                        .bold()
                        .multilineTextAlignment(.center)
                    Spacer()
                }
                .padding()
                .background(Color(.systemTeal))
                List {
                    Picker("Schools", selection: $user.school) {
                        ForEach(viewModel.schools, id: \.self) { school in
                            Text(school.uppercased())
                        }
                    }
                    .onChange(of: user.school) { _ in
                        user.saveToStorage()
                        Task {
                            do {
                                try await viewModel.initialize(user: user)
                            } catch MyError.runtimeError(let errorMessage) {
                                viewModel.error = errorMessage
                            }
                        }
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
                            viewModel.error = ""
                            viewModel.searching = true
                        } label: {
                            Text("Enter")
                        }
                        Spacer()
                    }
                }
                    
            }
            
            Spacer()
        }
    }
}
