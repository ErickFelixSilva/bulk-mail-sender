import React from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Nonprofit } from "../services/NonprofitService";
import { faCheckCircle, faTimesCircle } from "@fortawesome/free-solid-svg-icons";

interface NonprofitItemProps {
    nonprofit: Nonprofit;
    selectedNonprofits: number[];
    handleSelectNonprofit: (id: number) => void;
    handleEditNonprofit: (nonprofit: Nonprofit) => void;
    handleDeleteNonprofit: (id: number) => void;
}

function NonprofitItem({nonprofit, selectedNonprofits, handleSelectNonprofit, handleEditNonprofit, handleDeleteNonprofit }: NonprofitItemProps): JSX.Element {
    return (
        <tr key={nonprofit.id} className={`border-t ${nonprofit.emailSent ? 'bg-green-100' : ''}`}>
							<td className="py-2 px-4">
								<input
									type="checkbox"
									checked={selectedNonprofits.includes(nonprofit.id!)}
									onChange={() => handleSelectNonprofit(nonprofit.id!)}
								/>
							</td>
							<td className="py-2 px-4">{nonprofit.name}</td>
							<td className="py-2 px-4">{nonprofit.email}</td>
							<td className="py-2 px-4">{nonprofit.address}</td>
							<td className="py-2 px-4">
							{nonprofit.emailSent ? (<FontAwesomeIcon icon={faCheckCircle} className="text-green-500" />) :
                                (<FontAwesomeIcon icon={faTimesCircle} className="text-gray-500" />)}
							</td>
							<td className="py-2 px-4">
								<button
									className="bg-yellow-600 text-white px-2 py-1 rounded-lg hover:bg-yellow-700"
									onClick={() => handleEditNonprofit(nonprofit)}
								>
									Edit
								</button>
								<button
									className="bg-red-600 text-white px-2 py-1 rounded-lg hover:bg-red-700 ml-2"
									onClick={() => handleDeleteNonprofit(nonprofit.id!)}
								>
									Delete
								</button>
							</td>
						</tr>
    )
}

export default NonprofitItem;