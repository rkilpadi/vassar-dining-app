//
//  OpeningView.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/12/23.
//

import SwiftUI
import shared

struct OpeningView: View {
    
    @StateObject var viewModel: MenuViewModel
    @StateObject var user: User
    
    var body: some View {
        VStack (spacing: 0) {
            Spacer()
            List {
                Picker("Schools", selection: $user.school) {
                    ForEach(viewModel.schools, id: \.self) { school in
                        Text(school.uppercased())
                    }
                }
                .onChange(of: user.school) { _ in
                    user.saveToStorage()
                }
                .padding()
            }
            Spacer()
        }
    }
}
